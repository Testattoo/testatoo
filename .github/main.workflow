workflow "Push Workflow" {
  on = "push"
  resolves = ["Unit-tests"]
}

action "Unit-tests" {
  uses = "docker://tenjaa/java-chrome-nodejs"
  runs = "./mvnw test"
  secrets = ["GITHUB_TOKEN"]
}
