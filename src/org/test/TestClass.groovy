class TestClass implements Serializable {

    private pipelineSteps
    private static final TEST_STRING="Hello from shared lib"

    TestClass(pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    public def testMethod() {
        def col = ["rest", "/api/", null, "project/raw"]
        return col.findAll().collect {
            it.replaseAll("^/|/\$","")
        }.join("/")
    }
}
