/*
 * Copyright 2009 Kantega AS
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package no.kantega.publishing.rating.service;

import no.kantega.publishing.api.rating.Rating;

import java.util.List;

public interface RatingService {

    /**
     * Retrieves all ratings for a given object in a context
     *
     * @param objectId - E.g. a contentId or forumPostId.
     * @param context - Object context identifier
     * @return - All ratings for the object
     */
    public List<Rating> getRatingsForObject(String objectId, String context);

    /**
     * Deletes all ratins for a given object in a context.
     *
     * @param objectId - E.g. a contentId or forumPostId.
     * @param context - Object context identifier
     */
    public void deleteRatingsForObject(String objectId, String context);

    /**
     * Adds a rating or adjusts an existing rating.
     *
     * @param rating - The rating to save or update.
     */
    public void saveOrUpdateRating(Rating rating);

    /**
     * Returns all ratings given by a user.
     *
     * @param userId
     * @return
     */
    public List<Rating> getRatingsForUser(String userId);

    /**
     * Returns the relative score for all ratings an object has recieved.
     *
     * @param objectId - E.g. a contentId or forumPostId.
     * @param context - Object context identifier
     * @return A score comparable to scores givent to other objects in the same context.
     */
    public float getScoreForObject(String objectId, String context);
}
