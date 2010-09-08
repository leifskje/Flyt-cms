package no.kantega.publishing.search.control;

import no.kantega.commons.client.util.RequestParameters;
import no.kantega.commons.exception.ConfigurationException;
import no.kantega.commons.log.Log;
import no.kantega.publishing.common.Aksess;
import no.kantega.publishing.common.cache.DocumentTypeCache;
import no.kantega.publishing.common.data.Content;
import no.kantega.publishing.common.data.ContentIdentifier;
import no.kantega.publishing.common.data.ContentQuery;
import no.kantega.publishing.common.data.DocumentType;
import no.kantega.publishing.common.data.SortOrder;
import no.kantega.publishing.common.data.enums.ContentProperty;
import no.kantega.publishing.common.exception.ContentNotFoundException;
import no.kantega.publishing.common.service.ContentManagementService;
import no.kantega.publishing.controls.AksessController;
import no.kantega.publishing.search.SearchField;
import no.kantega.publishing.search.control.util.QueryStringGenerator;
import no.kantega.publishing.search.service.SearchService;
import no.kantega.publishing.search.service.SearchServiceQuery;
import no.kantega.publishing.search.service.SearchServiceResult;
import no.kantega.publishing.search.service.SearchServiceResultImpl;
import no.kantega.search.index.Fields;
import no.kantega.search.query.hitcount.DateHitCountQuery;
import no.kantega.search.query.hitcount.HitCountQuery;
import no.kantega.search.query.hitcount.HitCountQueryDefaultImpl;
import no.kantega.search.result.HitCount;
import no.kantega.search.result.SearchResultExtendedImpl;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ContentSearchController implements AksessController, InitializingBean {

    private String description = "Performs search for Aksess content";

    private SearchService searchService;
    private String queryStringEncoding = "iso-8859-1"; // Must match setting in web server, default for tomcat is iso-8859-1, jetty is utf-8. Tomcat can be set to use utf-8 by setting URIEncoding="UTF-8" on the Connector element in server.xml
    private List<SearchField> customSearchFields;

    private boolean hitCountDocumentType = true;
    private boolean hitCountParents = true;
    private boolean hitCountLastModified = true;

    static final String INVALIDQUERY = "invalidquery";

    private QueryStringGenerator queryStringGenerator;


    public Map handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();
        Map<String, Object> model = performSearches(request);
        model.put("totalTime", System.currentTimeMillis() - start);
        return model;
    }

    private Map<String, Object> performSearches(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<String, Object>();

        Content content = (Content)request.getAttribute("aksess_this");

        SearchServiceQuery query = createSearchServiceQuery(content, request);

        // Add hit counts
        addHitCountQueries(query, request, content);

        String urlPrefix = "?";

        // Perform search


        // SearchServiceResultImpl should be renamed or something in future
        SearchServiceResultImpl result = (SearchServiceResultImpl)searchService.search(query);


        if (result == null){
            model.put("error", INVALIDQUERY);
        } else{

            model.put("result", result);

            Map<String, Object> links = new HashMap<String, Object>();
            // QueryStrings for drilldown
            links.put("hitcounts", getHitCountUrls(urlPrefix, query, result));

            // QueryString to previous and next page
            String prevPageUrl = getPrevPageUrl(query, result);
            if (prevPageUrl != null) {
                links.put("prevPageUrl", urlPrefix + prevPageUrl);
            }
            String nextPageUrl = getNextPageUrl(query, result);
            if (nextPageUrl != null) {
                links.put("nextPageUrl", urlPrefix + nextPageUrl);
            }

            // QueryStrings for pages
            links.put("pageUrls", createPageUrls(urlPrefix, query, result));
            model.put("links", links);

        }
        return model;
    }

    private SearchServiceQuery createSearchServiceQuery(Content content, HttpServletRequest request) {
        SearchServiceQuery query = new SearchServiceQuery(request, customSearchFields);
        RequestParameters params = new RequestParameters(request);
        if (content != null) {
            query.putSearchParam("thisId", "" + content.getAssociation().getId());
            int siteId = params.getInt(SearchServiceQuery.PARAM_SITE_ID);
            if (siteId == -1) {
                siteId = content.getAssociation().getSiteId();
            }
            query.putSearchParam(SearchServiceQuery.PARAM_SITE_ID, "" + siteId);
        }
        return query;
    }

    /**
     * Get URL to previous page
     * @param query
     * @param result
     * @return
     */
    private String getPrevPageUrl(SearchServiceQuery query, SearchServiceResultImpl result) {
        String prevPageUrl = queryStringGenerator.prevPage(query, result.getCurrentPage());
        return prevPageUrl == null || "".equals(prevPageUrl) ? null : prevPageUrl;
    }

    /**
     * Get URL to next page
     * @param query
     * @param result
     * @return
     */
    private String getNextPageUrl(SearchServiceQuery query, SearchServiceResultImpl result) {
        String nextPageUrl = queryStringGenerator.nextPage(query, result.getCurrentPage(), query.getHitsPerPage(), result.getSearchResult().getNumberOfHits());
        return nextPageUrl == null || "".equals(nextPageUrl) ? null : nextPageUrl;
    }


    /**
     * Generate URL for navigation to pages in search result
     * @param urlPrefix
     * @param query - query
     * @param result - result
     * @return - list of URLs
     */
    private LinkedHashMap<String, String> createPageUrls(String urlPrefix, SearchServiceQuery query, SearchServiceResultImpl result) {
        LinkedHashMap<String, String> pageUrls = new LinkedHashMap<String, String>();
        int currentpage = result.getCurrentPage() + 1;
        int startPage = ((currentpage / 10) * 10) + 1;
        int endPage = startPage + 9;
        if (endPage * query.getHitsPerPage() >= result.getSearchResult().getNumberOfHits()) {
            endPage = (result.getSearchResult().getNumberOfHits() - 1) / query.getHitsPerPage();
            endPage++;
        }
        if (startPage > 1) {
            startPage--;
        }
        for (int i = startPage; i <= endPage; i++) {
            String[] keys = new String[]{ SearchServiceQuery.METAPARAM_PAGE };
            String[] values = new String[]{ "" + (i-1) };
            pageUrls.put("" + i, urlPrefix + queryStringGenerator.replaceParams(query, keys, values));
        }
        return pageUrls;
    }


    /**
     * Get links for drilldown
     * @param urlPrefix
     * @param query
     * @param result
     * @return
     */
    private Map<String, String> getHitCountUrls(String urlPrefix, SearchServiceQuery query, SearchServiceResult result) {
        Map<String, String> hitCounts = new HashMap<String, String>();
        SearchServiceResultImpl serviceResult = (SearchServiceResultImpl)result;

        if (serviceResult.getSearchResult() instanceof SearchResultExtendedImpl) {
            SearchResultExtendedImpl sr = (SearchResultExtendedImpl)serviceResult.getSearchResult();

            for (HitCount hitCount : sr.getHitCounts()) {
                if (Fields.LAST_MODIFIED.equals(hitCount.getField())) {
                    String url = queryStringGenerator.createLastModifiedUrl(query, hitCount);
                    if (url != null) {
                        String name = hitCount.getField() + "." + hitCount.getTerm();
                        hitCounts.put(name, urlPrefix + url);
                    }
                } else {
                    if (query.getStringParam(hitCount.getField()) == null || Fields.CONTENT_PARENTS.equals(hitCount.getField())) {
                        String name = hitCount.getField() + "." + hitCount.getTerm();
                        hitCounts.put(name, urlPrefix + queryStringGenerator.replaceParam(query, hitCount.getField(), hitCount.getTerm()));
                    }
                }
            }
        }
        return hitCounts;
    }

    private String[] getDocumentTypes() {
        List<DocumentType> documentTypes = DocumentTypeCache.getDocumentTypes();
        String[] docTypeIds = new String[documentTypes.size()];
        for (int i = 0; i < documentTypes.size(); i++) {
            docTypeIds[i] = "" + documentTypes.get(i).getId();
        }
        return docTypeIds;
    }

    /**
     * Gets a list of subpages based on parentid, if no parentid is specified, gets subpages under root
     * @param siteId
     * @param request
     * @return
     */
    private String[] getParents(int siteId, HttpServletRequest request) {
        ContentManagementService cms = new ContentManagementService(request);

        RequestParameters param = new RequestParameters(request);
        int parentId = param.getInt(Fields.CONTENT_PARENTS);

        try {
            ContentIdentifier cid;
            if (parentId == -1) {
                cid = new ContentIdentifier(siteId, "/");
            } else {
                cid = new ContentIdentifier();
                cid.setAssociationId(parentId);
            }
            ContentQuery query = new ContentQuery();
            query.setAssociatedId(cid);
            List<Content> pages = cms.getContentSummaryList(query, -1, new SortOrder(ContentProperty.TITLE, false));
            String[] parents = new String[pages.size()];
            for (int i = 0; i < pages.size(); i++) {
                parents[i] = "" + pages.get(i).getAssociation().getId();
            }
            return parents;
        } catch (ContentNotFoundException e) {
            Log.error(this.getClass().getName(), e, null, null);
        }

        return null;
    }

    /**
     * Creates queries for hit counts, eg hits per category
     * @param query - SearchServiceQuery
     * @param request - HttpServletRequest
     * @param content - Content current page
     */
    protected void addHitCountQueries(SearchServiceQuery query, HttpServletRequest request, Content content) {
        if (hitCountDocumentType) {
            // Document types
            HitCountQuery hitCountDocType = new HitCountQueryDefaultImpl(Fields.DOCUMENT_TYPE_ID, HitCountHelper.getDocumentTypes(), true);
            query.addHitCountQuery(hitCountDocType);
        }

        if (hitCountParents) {
            // Parents
            int siteId = 1;
            if (content != null) {
                siteId = content.getAssociation().getSiteId();
            }
            HitCountQuery hitCountParents = new HitCountQueryDefaultImpl(Fields.CONTENT_PARENTS, HitCountHelper.getParents(siteId, request), true);
            query.addHitCountQuery(hitCountParents);
        }

        if (hitCountLastModified) {
            // Modified date
            query.addHitCountQuery(new DateHitCountQuery(Fields.LAST_MODIFIED, 5, null, null));
        }

        addCustomQueries(query, request, content);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public void setHitCountDocumentType(boolean hitCountDocumentType) {
        this.hitCountDocumentType = hitCountDocumentType;
    }

    public void setHitCountParents(boolean hitCountParents) {
        this.hitCountParents = hitCountParents;
    }

    public void setHitCountLastModified(boolean hitCountLastModified) {
        this.hitCountLastModified = hitCountLastModified;
    }

    public void setQueryStringEncoding(String queryStringEncoding) {
        this.queryStringEncoding = queryStringEncoding;
    }

    public void setCustomSearchFields(List<SearchField> customSearchFields) {
        this.customSearchFields = customSearchFields;
    }

    public void afterPropertiesSet() throws Exception {
        try {
            queryStringEncoding = Aksess.getConfiguration().getString("querystring.encoding", queryStringEncoding);
            Log.info(this.getClass().getName(), "Using " + queryStringEncoding + " query string encoding.  Set querystring.encoding to match web server setting if necessary");
            queryStringGenerator = new QueryStringGenerator(queryStringEncoding);
        } catch (ConfigurationException e) {
            Log.error(this.getClass().getName(), e, null, null);
        }
    }

    private void addCustomQueries(SearchServiceQuery query, HttpServletRequest request, Content content) {
        if (customSearchFields != null) {
            for (SearchField field : customSearchFields) {
                List<HitCountQuery> hitCountQueries = field.getHitCountQueries(query, request, content);
                if (hitCountQueries != null) {
                    for (HitCountQuery hcQuery : hitCountQueries) {
                        query.addHitCountQuery(hcQuery);
                    }
                }
            }
        }

    }

}
