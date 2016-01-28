package org.bpoller.proxytest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ContainerTest {

    private static final Logger logger = LoggerFactory.getLogger(ContainerTest.class);


    @Test
    public void shouldWork() {

        logger.info("I am logging !");
        assertTrue("This should not fail", true);
    }

}
