package org.test

@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method

class BitBucket implements Serializable {
    
    private static final Integer BITBUCKET_LIMIT = 100
    private String serverUri, projectUri, project, repoName, bbUser, bbPasswd, repoUri
    private pipelineSteps 
   
    BitBucket(pipelineSteps, String bbUser,
          String bbPasswd,
          String serverUri="",
          String project="",
          String repoName="") {
    this.pipelineSteps = pipelineSteps
    this.serverUri = serverUri
    this.project = project
    this.repoName = repoName
    this.bbUser = bbUser
    this.bbPasswd = bbPasswd
    this.projectUri = "rest/api/1.0/projects/${project}"
    this.repoUri = "rest/api/1.0/projects/${project}/repos/${repoName}"
    
    String getLog() {
        
}
