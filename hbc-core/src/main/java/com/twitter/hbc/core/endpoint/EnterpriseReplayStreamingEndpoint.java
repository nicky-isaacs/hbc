/**
 * Copyright 2014 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.twitter.hbc.core.endpoint;

import com.google.common.base.Preconditions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EnterpriseReplayStreamingEndpoint extends EnterpriseStreamingEndpoint {
  protected Date fromDate;
  protected Date toDate;
  private static final String DATE_FMT_STR = "yyyyMMddHHmm";
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FMT_STR);

  protected static final String BASE_PATH = "/accounts/%s/publishers/twitter/replay/track/%s.json";

  public EnterpriseReplayStreamingEndpoint(String account, String label, Date fromDate, Date toDate) {
    super(account, label);

    this.fromDate        = Preconditions.checkNotNull(fromDate);
    this.toDate          = Preconditions.checkNotNull(toDate);
  }

  @Override
  public String getURI() {
    String uri = String.format(BASE_PATH, this.account.trim(), this.label.trim());
    String queryString;

    String _toDate   = formatDate(this.toDate);
    String _fromDate = formatDate(this.fromDate);
    addQueryParameter("toDate", _toDate);
    addQueryParameter("fromDate", _fromDate);

    queryString = "?" + generateParamString(this.queryParameters);

    return uri + queryString;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  private String formatDate(Date date) {
    return DATE_FORMAT.format(date);
  }


}
