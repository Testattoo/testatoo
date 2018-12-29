workflow "Push Workflow" {
  on = "push"
  resolves = ["Unit-tests"]
}

action "Unit-tests" {
  uses = "docker://circleci/openjdk"
  runs = "./mvnw test"
  secrets = ["GITHUB_TOKEN"]
}
