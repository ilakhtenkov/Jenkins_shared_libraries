@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import jenkins.model.*

def call (groupId, repositoryId, nexusServer, credId) {
    def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
            com.cloudbees.plugins.credentials.Credentials.class,
            Jenkins.instance,
            null,
            null
    );
    def credential = creds.find {it.id == credId}
    def http = new HTTPBuilder ( nexusServer )
    http.headers[ 'Authorization' ] = "Basic " + "${credential.username}:${credential.password}".getBytes('iso-8859-1').encodeBase64()
    http.request( GET, TEXT ) {
        uri.path = "service/local/repositories/" + repositoryId + "/index_content/" + groupId.replaceAll('\\.','/') + "/"
        headers.Accept = 'application/xml'
        response.success = { resp, reader ->
            def indexBrowserTreeViewResponse = new XmlParser().parseText(reader.text)
            return indexBrowserTreeViewResponse.data.children.indexBrowserTreeNode.children.indexBrowserTreeNode.'version'*.text().sort().reverse()
        }
        response.failure = { resp ->
            return 'Response status='+resp.status
        }
    }

}