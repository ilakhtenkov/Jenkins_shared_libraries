
def call(state, stage) {
    def causes = currentBuild.rawBuild.getCauses()
    def cause = null
    if (!causes.isEmpty()) {
        cause = causes[0].getShortDescription()
    }
    log = currentBuild.rawBuild.getLog(40).join('\n')
    causes = null
    def body = """
                    <p>Build $env.BUILD_NUMBER ran on $env.NODE_NAME and ended with $currentBuild.result .
                    </p>
                    <p><b>Build trigger</b>: $cause</p>
                    <p><b> Check response code</b>: response </p>
                    <p>See: <a href="$env.BUILD_URL">$env.BUILD_URL</a></p>
                """
    if  (state != 'SUCCESS') {
        body = body + """
            <p><b>Failed on stage</b>: $current_stage</p>
            <h2>Last lines of output:</h2>
            <pre>$log</pre>
        """
    }

    emailext attachLog: true, body: body ,
            compressLog: true,
            subject: "$env.JOB_NAME $env.BUILD_NUMBER: $currentBuild.result",
            to: emailextrecipients([[$class: 'UpstreamComitterRecipientProvider'],
                                    [$class: 'FailingTestSuspectsRecipientProvider'],
                                    [$class: 'FirstFailingBuildSuspectsRecipientProvider'],
                                    [$class: 'CulpritsRecipientProvider'],
                                    [$class: 'DevelopersRecipientProvider'],
                                    [$class: 'RequesterRecipientProvider']])
}



