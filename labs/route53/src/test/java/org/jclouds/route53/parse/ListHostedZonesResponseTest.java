/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.route53.parse;

import static org.testng.Assert.assertEquals;

import java.io.InputStream;

import org.jclouds.collect.IterableWithMarker;
import org.jclouds.collect.IterableWithMarkers;
import org.jclouds.http.functions.BaseHandlerTest;
import org.jclouds.route53.domain.Zone;
import org.jclouds.route53.xml.ListHostedZonesResponseHandler;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableSet;

/**
 * @author Adrian Cole
 */
// NOTE:without testName, this will not call @Before* and fail w/NPE during surefire
@Test(groups = "unit", testName = "ListHostedZonesResponseTest")
public class ListHostedZonesResponseTest extends BaseHandlerTest {

   public void test() {
      InputStream is = getClass().getResourceAsStream("/hosted_zones.xml");

      IterableWithMarker<Zone> expected = expected();

      ListHostedZonesResponseHandler handler = injector.getInstance(ListHostedZonesResponseHandler.class);
      IterableWithMarker<Zone> result = factory.create(handler).parse(is);

      assertEquals(result.toString(), expected.toString());

   }

   public IterableWithMarker<Zone> expected() {
      return IterableWithMarkers.from(
            ImmutableSet.of(
                  Zone.builder()
                      .id("/hostedzone/Z21DW1QVGID6NG")
                      .name("example.com.")
                      .callerReference("a_unique_reference")
                      .resourceRecordSetCount(17)
                      .comment("Migrate an existing domain to Route 53").build(),
                  Zone.builder()
                      .id("/hostedzone/Z2682N5HXP0BZ4")
                      .name("example2.com.")
                      .callerReference("a_unique_reference2")
                      .resourceRecordSetCount(117)
                      .comment("This is my 2nd hosted zone.").build()), "Z333333YYYYYYY");
   }
}
