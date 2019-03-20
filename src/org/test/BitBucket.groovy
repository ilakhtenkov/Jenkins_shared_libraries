package org.test

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class BitBucket implements Serializable {
    private static final Integer BITBUCKET_LIMIT = 100
}
