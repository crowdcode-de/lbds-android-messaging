pipeline {
    agent { label 'Android-SDK-Manager-gradle' }
    options {
        office365ConnectorWebhooks([[
                                            offsetNotification: true,
                                            notifySuccess: true,
                                            notifyFailure: true,
                                            notifyUnstable: true,
                                            notifyBackToNormal: true,
                                            url: "${env.TEAMS_LBDS_WEBHOOK_URL}"
                                    ]]
        )
    }
    stages {
        stage('Prepare') {
            steps {
                withCredentials(
                        [usernamePassword(credentialsId: 'CSchemmy',
                                usernameVariable: 'gitUser',
                                passwordVariable: 'gitPwd'
                        )]) {
                    script {
                        sh "git status"
                        sh "git checkout ${env.BRANCH_NAME}"
                        sh "git reset --hard origin/${env.BRANCH_NAME}"
                        pom = readMavenPom file: 'pom.xml'
                        final orginalVersion = pom.version
                        mvn("-DfailOnMissingBranchId=false -Dnamespace=org.hzi -DbranchName=${env.BRANCH_NAME} -Dgituser=${gituser} -Dgitpassword=${gitPwd} io.crowdcode:bgav-maven-plugin:1.1.0:bgav")
                        pom = readMavenPom file: 'pom.xml'
                        final newVersion = pom.version

                        aarPom = readMavenPom file: 'pom-aar.xml'
                        final orginalAarVersion = aarPom.version
                        mvn("-DfailOnMissingBranchId=false -DpomFile=pom-aar.xml -Dnamespace=org.hzi -DbranchName=${env.BRANCH_NAME} -Dgituser=${gituser} -Dgitpassword=${gitPwd} io.crowdcode:bgav-maven-plugin:1.1.0:bgav")
                        aarPom = readMavenPom file: 'pom-aar.xml'
                        final newAarVersion = aarPom.version

                        if (!orginalVersion.equals(newVersion) || !orginalAarVersion.equals(newAarVersion)) {
                            sh "mkdir -p target && touch target/DO_NOT_BUILD"
                            env.DO_NOT_BUILD=true
                        } else {
                            env.DO_NOT_BUILD=false
                        }
                    }
                }
            }
        }
        stage('Build jar') {
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps {  mvn("clean install") }
        }
        stage('Deploy jar') {
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps { mvn("deploy -DskipTests=true") }
        }
        stage('Build aar') {
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps {  mvn("clean install -f pom-aar.xml") }
        }
        stage('Deploy aar') {
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps { mvn("deploy -DskipTests=true -f pom-aar.xml") }
        }
    }
}
def mvn(param) {
    withMaven(
            // globalMavenSettingsConfig: 'GlobalSettingsNexus',
            options: [openTasksPublisher(disabled: true)],
            mavenOpts: '-Xmx1536m -Xms512m',
            maven: 'maven-3.6.3') {
        sh "mvn -U -B -e ${param}"
    }
}