# sam-portfolio

AWS SAM function for static personal portfolio website.

## Tech Stack

**Server:** Java 11, AWS SAM, API Gateway, DynamoDB

## Run Locally

Clone the project

```bash
  git clone https://github.com/gramoszuniga/sam-portfolio
```

Go to the project directory

```bash
  cd sam-portfolio
```

Install dependencies and build SAM function

```bash
  sam build
```

Start API Gateway locally

```bash
  sam local start-api
```

Test end-points

```bash
    curl --request GET http://localhost:3000/portfolio
    curl --request PUT http://localhost:3000/portfolio
```

## Acknowledgements

- [AWS SDK for Java 2.0](https://github.com/aws/aws-sdk-java-v2)