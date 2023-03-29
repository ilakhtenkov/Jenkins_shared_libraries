import com.test.Constants

void call(Map config) {
    def constants = readYaml(text: libraryResource('constants.yaml'))
    println("f2: ${constants.migratedNamespaces}")
    println("f2 from class: ${Constants.migratedNamespaces}")
}
