/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package jenkins

class CreateIntegTestMarkDownTable {
    String version
    ArrayList<String> tableData

    CreateIntegTestMarkDownTable(String version, List<Map<String, Object>> tableData) {
        this.version = version
        this.tableData = tableData
    }

    def create() {

        def tableHeader = """
### Integration Test Failed for version ${version}. See the specifications below:

#### Details

| Platform | Distribution | Architecture | Test Report Manifest | Workflow Run |
|----------|--------------|--------------|----------------------|--------------|
"""
        def tableRows = this.tableData.collect { row ->
            "| ${row.platform.toString()} | ${row.distribution.toString()} | ${row.architecture.toString()} | ${row.test_report_manifest_yml.toString()} | ${row.integ_test_build_url.toString()}"
        }.join("\n")

        def additionalInformation = """
\nCheck out test report manifest linked above for steps to reproduce, cluster and integration test failure logs. For additional information checkout the [wiki](https://github.com/opensearch-project/opensearch-build/wiki/Testing-the-Distribution) and [OpenSearch Metrics Dashboard](https://metrics.opensearch.org/_dashboards/app/dashboards#/view/21aad140-49f6-11ef-bbdd-39a9b324a5aa).
"""
        return tableHeader + tableRows + additionalInformation
    }

}