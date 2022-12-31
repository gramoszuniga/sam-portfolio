# sam-portfolio

AWS SAM function for static personal portfolio website.

## Tech Stack

**Server:** Java 11, AWS SAM, DynamoDB

## Run Locally

Clone the project

```bash
  git clone https://github.com/gramoszuniga/sam-portfolio
```

Go to the project directory

```bash
  cd sam-portfolio
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  sam local invoke
```

## Acknowledgements

- [AWS SDK for Java 2.0](https://github.com/aws/aws-sdk-java-v2)