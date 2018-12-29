workflow "Push Workflow" {
  on = "push"
  resolves = ["Unit-tests"]
}

action "Unit-tests" {
  uses = "docker://atlassian/docker-node-jdk-chrome-firefox"
  runs = "./mvnw test"
  secrets = ["GITHUB_TOKEN"]
}
