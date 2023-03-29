void call(Map config) {
    def constants = readYaml(text: libraryResource('constants.yaml'))
    // usage example1:
    print("f1: ${constants.migratedNamespaces}")
    //usage example2:
    constants.each { k,v -> this."${k}" = v  }
    print("f1: ${migratedNamespaces}")
}
