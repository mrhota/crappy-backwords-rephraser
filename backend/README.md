# Documentation

- enable google Cloud Translation API
- generate Cloud Translation API credentials
- create a Service Account with the role "Cloud Translation API User"
  - need to provide clientLibraryConfig-personal-aws-provider.json as a file in the docker image instead of inside jar?

# commands

- `bb hl:compile` if I change any clj stuff
- `sam build` if I change the lambda details (like `template.yml` or Dockerfile)
- `sam local invoke ExampleLambdaFunction` to test locally

## frontend

- `clj -M:build`
- `cp out/main.js ../../../misterajazz/static/js/b.js`
