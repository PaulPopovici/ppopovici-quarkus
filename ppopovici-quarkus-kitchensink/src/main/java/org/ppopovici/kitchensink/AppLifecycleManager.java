package org.ppopovici.kitchensink;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.enterprise.context.control.ActivateRequestContext;

/**
 * The type App lifecycle manager.
 */
public class AppLifecycleManager implements QuarkusApplication
{
    @Override
    @ActivateRequestContext
    public int run( String... args )
    {
        Log.info( "Running business logic..." );
        Quarkus.waitForExit( );
        return 0;
    }
}
