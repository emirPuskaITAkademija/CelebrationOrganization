package org.celebration.celebrationorganization.api;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
//http://localhost:8080/CelebrationOrganization-1.0-SNAPSHOT/api/

/**
 * @ApplicationPath -> definira URL koji okida pozive REST servisa
 */
@ApplicationPath("/api")
public class CelebrationRestConfig extends Application {
}
