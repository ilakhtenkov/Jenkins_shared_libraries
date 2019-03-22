class TestClass implements Serializable {

    private pipelineSteps
    private static final TEST_STRING="Hello from shared lib"

    TestClass(pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    public def testMethod(...args) {
        //def col = ["rest", "/api/", null, "project/raw"]
        return args.findAll().collect {
            it.replaceAll("^/|/\$","")
        }.join("/")
    }
}
