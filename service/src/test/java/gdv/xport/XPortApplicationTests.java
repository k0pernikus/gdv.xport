/*
 * Copyright (c) 2017 by Oli B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 13.02.2017 by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import gdv.xport.service.XPortApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit-Tests fuer die {@link XPortApplication}-Klasse.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XPortApplication.class)
public class XPortApplicationTests {

    /**
     * Momentan ist dies nur ein Test, ob die Spring-Einstellungen stimmen.
     */
	@Test
	public void contextLoads() {
	}

}
