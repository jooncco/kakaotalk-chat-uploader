## Kakaotalk Chat Uploader

Kakaotalk is one of the most popular messenger in Korea.  
This Spring Boot application is an uploader for kakaotalk chat dump files which reads & parses that text file and uploads utterances line by line to a Amazon S3 bucket.

Usage example: I want to upload last 30 minutes chat from a dump file.

```zsh
./gradlew bR --args="--sourceFilePath=src/main/resources/chat/chat_dump.txt --minutes=30"
```

`sourceFilePath` and `minutes` argument above are required. 

This app uses:

- `Localstack`: a local AWS mock environment for testing.
- `AmazonS3Client`: a client for Amazon S3 upload.

### Setup

#### 1. Install Localstack

AWS CLI
```zsh
brew install awscli
```

Localstack
```zsh
pip install awscli-local
```

```zsh
awslocal --version
```

Create test bucket
```zsh
awslocal s3api create-bucket --bucket test-bucket
```

#### 2. Container Runtime

Rancher Desktop
```zsh
brew install --cask rancher
```

Create `kakaotalk-chat-uploader` network
```zsh
docker network create kakaotalk-chat-uploader
```

#### 3. Docker Compose

Directory below will be mounted for localstack volume when we do docker compose.
```zsh
mkdir src/test/resources/localstack
```

Docker compose
```zsh
docker-compose up -d
```

### Run

```zsh
./gradlew bR --args="--sourceFilePath=src/main/resources/chat/chat_dump.txt --minutes=30"
```