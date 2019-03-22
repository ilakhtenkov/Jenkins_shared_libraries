class TestClass implements Serializable {

    private pipelineSteps
    private static final TEST_STRING="Hello from shared lib"

    TestClass(pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }


    public def testMethod(String item, String id, String property) {
        def url = join(item, id, property)
        url += "/test_part"
        pipelineSteps.log.info "I'm here"
        return url
    }

    private def join(...args) {
        //def col = ["rest", "/api/", null, "project/raw"]
        return args.findAll().collect {
            it.replaceAll("^/|/\$","")
        }.join("/")
    }
}
