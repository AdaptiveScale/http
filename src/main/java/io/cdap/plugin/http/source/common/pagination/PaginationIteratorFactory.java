/*
 * Copyright © 2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.cdap.plugin.http.source.common.pagination;

import io.cdap.plugin.http.source.common.BaseHttpSourceConfig;

/**
 * A factory which creates instance of {@BaseHttpPaginationIterator} in accordance to pagination type configured in
 * the input config.
 */
public class PaginationIteratorFactory {
  public static BaseHttpPaginationIterator createInstance(BaseHttpSourceConfig config) {
    switch (config.getPaginationType()) {
      case NONE:
        return new NonePaginationIterator(config);
      case LINK_IN_RESPONSE_HEADER:
        return new LinkInResponseHeaderPaginationIterator(config);
      case LINK_IN_RESPONSE_BODY:
        return new LinkInResponseBodyPaginationIterator(config);
      case TOKEN_IN_RESPONSE_BODY:
        return new TokenPaginationIterator(config);
      case INCREMENT_AN_INDEX:
        return new IncrementAnIndexPaginationIterator(config);
      case CUSTOM:
        return new CustomPaginationIterator(config);
      default:
        throw new IllegalArgumentException(
          String.format("Unsupported pagination type: '%s'", config.getPaginationType()));
    }
  }
}
