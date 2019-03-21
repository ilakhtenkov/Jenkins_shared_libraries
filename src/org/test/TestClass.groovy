class TestClass implements Serializable {

    private pipelineSteps

    TestClass(pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    public static def getLog() {
        return currentBuild.rawBuild.getLog()
    }
}
