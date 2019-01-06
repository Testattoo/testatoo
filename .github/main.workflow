workflow "Push Workflow" {
  on = "push"
  resolves = ["Unit-tests"]
}

action "Unit-tests" {
  uses = "docker://circleci/openjdk:8-jdk"
  runs = "./mvnw test -Dremote=true"
  secrets = ["GITHUB_TOKEN"]
}
