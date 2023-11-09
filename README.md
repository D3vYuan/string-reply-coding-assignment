<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<div id="top"></div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h2 align="center">String Reply v2</h2>
  <p align="center">
    <a href="https://github.com/D3vYuan/string-reply-coding-assignment/actions/workflows/main.yml">
        <img src="https://github.com/D3vYuan/string-reply-coding-assignment/actions/workflows/main.yml/badge.svg" alt="Deploy to AWS Elasticbean Stalk"/>
    </a>
    <a href="https://github.com/D3vYuan/string-reply-coding-assignment/actions/workflows/jacoco-badge.yml">
        <img src=".github/badges/jacoco.svg" alt="JaCoCo Test Coverage"/>
    </a>
    <a href="https://codeclimate.com/github/D3vYuan/string-reply-coding-assignment/maintainability">
        <img src="https://api.codeclimate.com/v1/badges/069d23a4da9969dc4c29/maintainability" alt="CodeClimate Maintanability"/>
    </a>
    <a href="https://snyk.io/test/github/D3vYuan/string-reply-coding-assignment">
        <img src="https://snyk.io/test/github/D3vYuan/string-reply-coding-assignment/badge.svg" alt="Synk Security"/>
    </a>
    <a href="http://hits.dwyl.com/D3vYuan/D3vYuan/string-reply-coding-assignment">
        <img src="https://hits.dwyl.com/D3vYuan/D3vYuan/string-reply-coding-assignment.svg?style=flat&show=unique" alt="Repo View Count"/>
    </a>
  </p>
  <!--div>
    <img src="images/profile_pic.png" alt="Logo" width="80" height="80">
  </div-->
</div>

<!-- TABLE OF CONTENTS -->

<!-- ## Table of Contents -->

<!-- <details> -->
<ol>
    <li>
        <a href="#about-the-project">About The Project</a>
        <ul>
            <li><a href="#requirements">Requirements</a></li>
            <li><a href="#use-case">Use Case</a></li>
        </ul>
    </li>
    <li>
        <a href="#design">Design</a>
        <ul>
            <li><a href="#reverse-string-design">Reverse String Design</a></li>
            <li><a href="#hash-string-design">Hash String Design</a></li>
        </ul>
    </li>
    <li>
        <a href="#implementation">Implementation</a>
        <ul>
            <li><a href="#controller-v2-implementation">String Reply v2 Controller</a></li>
            <li><a href="#reverse-string-implementation">Reverse String Implementation</a></li>
            <li><a href="#hash-string-implementation">Hash String Implementation</a></li>
        </ul>
    </li>
    <li>
        <a href="#security">Security</a>
        <ul>
            <li><a href="#basic-authentication">Basic Authentication</a></li>
        </ul>
    </li>
    <li>
        <a href="#testing">Testing</a>
        <ul>
            <li><a href="#unit-testing">Unit Testing</a></li>
            <li><a href="#integration-testing">Integration Testing</a></li>
        </ul>
    </li>
    <li>
        <a href="#documentation">Documentation</a>
        <ul>
            <li><a href="#swagger">Swagger</a></li>
        </ul>
    </li>
    <li>
        <a href="#deployment">Deployment</a>
        <ul>
            <li><a href="#gradle">Gradle</a></li>
            <li><a href="#github-actions">Github Actions</a></li>
            <li><a href="#aws-elasticbeanstalk">AWS Elasticbeanstalk</a></li>
        </ul>
    </li>
    <li>
        <a href="#Usage">Usage</a>
        <ul>
            <li><a href="#via-swagger">Via Swagger</a></li>
        </ul>
    </li>
    <li>
        <a href="#future-considerations">Future Considerations</a>
        <ul>
            <li><a href="#database-integration">Database Integration</a></li>
            <li><a href="#cache-integration">Cache Integration</a></li>
            <li><a href="#oauth-integration">OAuth Integration</a></li>
        </ul>
    </li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
</ol>
<!-- </details> -->

<!-- ABOUT THE PROJECT -->

## About The Project

This project is created to upgrade the `String Reply Service` with the following new features:
* Input String which consists of two components, a rule and a string, separated by a dash (-)
    * `1`: reverse the string <br>
    E.g. `kbzw9ru` becomes `ur9wzbk`
    * `2`: encode the string via MD5 hash algorithm and display as hex
    E.g. `kbzw9ru` becomes `0fafeaae780954464c1b29f765861fad`
* The numbers are applied in sequence, i.e. the output of the `first` rule will serve as the input of the `second` rule


<p align="right">(<a href="#top">back to top</a>)</p>

### Requirements

The following are some of the requirements for the new features:
* Maintain the existing endpoint for `backward` compatibility
* Implement `V2 endpoint` with the above new features
* `Additional` rules are expected in future releases. The updates in rule set should have minimal code changes and impact to existing functionality
* `Testability` for individual rule and the application. Unit tests are highly recommended
* Endpoints should return correct `status code` and `response message`. For invalid request, it should return status code 400 with message *"Invalid input"*

<p align="right">(<a href="#top">back to top</a>)</p>

### Use Case

| ![Use-case][img-use-case] |
|:--:|
| *Use Case* |

The above shows a summary of what a user can do with the `original` endpoint and the `new` endpoint. For the `original` endpoint, it will just return the input string as the response. As for the `new` endpoint, depending on the ruleset, it will return the string either reversed or hashed.

<p align="right">(<a href="#top">back to top</a>)</p>

### Example
The following are some examples for the the `new` endpoint:
* `Rule 11` - Rule that indicates the string should be reversed, then reversed back again
* `Rule 12` - Rule that indicates the string should be reversed first, then hashed with the reversed value
* `Rule 22` - Rule that indicates the string should be hashed twice
* `Invalid Rule` - Rule that are not within `1` or `2`

<p align="right">(<a href="#top">back to top</a>)</p>

**Rule 11**
For this example, `kbzw9ru` will still be `kbzw9ru` since it is reversed twice

```bash
GET /v2/reply/11-kbzw9ru
{
    "data": "kbzw9ru"
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

**Rule 12**

For this example, `kbzw9ru` will become `5a8973b3b1fafaeaadf10e195c6e1dd4`, where it is first reversed then hashed

```bash
GET /v2/reply/12-kbzw9ru
{
    "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

**Rule 22**

For this example, `kbzw9ru` will become `e8501e64cf0a9fa45e3c25aa9e77ffd5`, where it is hashed twice

```bash
GET /v2/reply/22-kbzw9ru
{
    "data": "e8501e64cf0a9fa45e3c25aa9e77ffd5"
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

**Invalid Rule**

For this example, `kbzw9ru` will yield `Invalid input` since `3` is not a supported rule

```bash
GET /v2/reply/13-kbzw9ru
{
    "message": "Invalid input"
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Design -->

## Design

### Reverse String Design

<div id="reverse-string-design"></div>

| ![Reverse-String-Design][img-reverse-string-design] |
|:--:|
| *Reverse String Design* |

The above shows the flow of the `reverse string` request:
1. The *user* will initiate a request to the **String Reply v2** providing the necessary input (E.g *11-kbzw9ru*)
2. Upon received the request, the *controller* will pass the message to the *service* to get the processed string
3. The *service* will then split the message to extract the *rule* and pass the *rule* to the *factory* to retrieve the corresponding *processor*
4. *Factory* will search through its list to determine if a *processor* exists for the particular rule. If it exists, it will return that *processor*. Otherwise, it will throw an exception
5. With the *processor*, the *service* can then pass the message where it will perform the string reversal and return the result back to *service*
6. The *service* will then return the reversed string to the *controller*
7. The *controller* will format the response with the reversed string and return it to the *user*

<p align="right">(<a href="#top">back to top</a>)</p>

### Hash String Design

<div id="hash-string-design"></div>

| ![Hash-String-Design][img-hash-string-design] |
|:--:|
| *Hash String Design* |

The above shows the flow of the `hash string` request:
1. The *user* will initiate a request to the **String Reply v2** providing the necessary input (E.g *22-kbzw9ru*)
2. Upon received the request, the *controller* will pass the message to the *service* to get the processed string
3. The *service* will then split the message to extract the *rule* and pass the *rule* to the *factory* to retrieve the corresponding *processor*
4. *Factory* will search through its list to determine if a *processor* exists for the particular rule. If it exists, it will return that *processor*. Otherwise, it will throw an exception
5. With the *processor*, the *service* can then pass the message where it will perform the string hashing and return the result back to *service*
6. The *service* will then return the hashed string to the *controller*
7. The *controller* will format the response with the hashed string and return it to the *user*

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- Implementation -->

## Implementation

### Controller v2 Implementation

**ReplyControllerv2.java**

Since we need to ensure `backward compatibility`, we will create the new features with a new *controller* and by setting the `@RequestMapping`, we were able to set the endpoint. <br/>

In addition, since we are expecting the input string to have a rule and a string, separated by dash (-), the `@GetMapping` was created with the expected pattern defined<br/>

```java
@RestController
@RequestMapping("/v2")
public class ReplyControllerv2 {
    ...
    @GetMapping("/reply/{message:^[0-9]{2}-.*}")
    public ReplyMessage replying(@PathVariable String message) throws InvalidInputException {
        return replyService.process(message);
    }
}
```

<br>

**ReplyService.java**

The *service* will perform the following:
* Split the message to extract the *rules* 
* Retrieve the corresponding *processors*
* Pass message to the *processors*
* Return processed message to *controller*

One of the requirement is that of `extendability`, in the following implementation, the `MessageProcessor` is an interface that is implemented by all the *processor* for the respective rules. <br/>

Whenever, there are new rules to be added, we just need to create a new *processor* ny implementing the *MessageProcessor* and adding the new implementation into `MessaageProcessorEnum` and it will be able to work without changing any codes in `ReplyService`

```java
public ReplyMessage process(String message) throws InvalidInputException {
    if (StringUtils.isAllBlank(message)) {
        return new ReplyMessage("Message is empty");
    }

    String[] messageParts = message.split("-");

    String messageRules = messageParts[0];
    String originalMessage = messageParts[1];

    String[] messageRulesParts = messageRules.split("");
    String processedMessage = originalMessage;

    for (int i = 0; i < messageRulesParts.length; i++) {
        String currentRule = messageRulesParts[i];
        Optional<MessageProcessorEnum> processorOptional = MessageProcessorEnum.getProcessorByID(currentRule);
        if (!processorOptional.isPresent()) {
            throw new InvalidInputException(String.format("Rule #%s is not supported", currentRule));
        }

        MessageProcessorEnum processorEnum = processorOptional.get();
        MessageProcessor processor = processorEnum.getProcessor();
        processedMessage = processor.process(processedMessage);
    }

    return new ReplyMessage(processedMessage);
}
```

<br>

**MessageProcessorEnum.java**

`MessageProcessorEnum` contains the supported processors for the respective rules and it also have a reverse lookup to return the *processor* based on the rule provided

```java
REVERSE("1", "Reverse the string", new ReverseMessage()),
ENCODE("2", "Encode the string with MD5 hash alogrithm", new EncodeMessage());

public static Optional<MessageProcessorEnum> getProcessorByID(String processorId) {
		return Arrays.stream(MessageProcessorEnum.values())
				.filter(processor -> processor.getProcessorID().equals(processorId)).findAny();
	}
```

<p align="right">(<a href="#top">back to top</a>)</p>

### Reverse String Implementation

**ReverseMessage.java**

`ReverseMessage` is one of the *processors*, and it will  perform string reversal whenever rule `1` is found

```java
public class ReverseMessage implements MessageProcessor {
    @Override
    public String process(String message) {
        return StringUtility.reverseString(message);
    }
}
```

<br>

**StringUtility.java**

The *reverseString* method in `StringUtility` provides the actual implementation for reversing the string

```java
public static String reverseString(String message) {
    if (StringUtils.isAllBlank(message)) {
        return MessageConstant.INVALID_INPUT;
    }
    return StringUtils.reverse(message);
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

### Hash String Implementation

**EncodeMessage.java**

`EncodeMessage` is one of the *processors*, and it will  perform string reversal whenever rule `2` is found

```java
public class EncodeMessage implements MessageProcessor {
    @Override
    public String process(String message) {
        return StringUtility.hashString(message);
    }
}
```

<br>

**StringUtility.java**

The *reverseString* method in `StringUtility` provides the actual implementation for encoding the string with MD5 hash and displaying as hex

```java
public static String hashString(String message) {
    if (StringUtils.isAllBlank(message)) {
        return MessageConstant.INVALID_INPUT;
    }

    return DigestUtils.md5Hex(message);
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- SECURITY -->

## Security

### Basic Authentication

We will be enabling the `Basic Authentication` and apply to all the endpoints using `Spring Security`
<br>

Once the `Basic Authentication` is enabled, all the Restful API will need to be pass in the `username` and `password` in the header of the request

**application.properties**

To facilitate testing for this project, a `dummy user` and `password` is specify for this project inside the properties file. In actual production, one should connect to a database or ldap to retrieve the user info and permission.

```
#*************************
# Security Properties
#*************************
spring.security.user.name=username
spring.security.user.password=password
```

**SecurityConfig.java**

The *config* will perform the following:
* Disable the `csrf` - This is more for *PUT* and *POST* http methods
* Enable `Basic Authentication`
* Make the session `stateless` since Restful API does not require state
* Specify the which urls link should be *unauthenticated* or *authenticated*

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeRequests(requests -> requests
                .antMatchers(SecurityConstant.SWAGGER_WHITELIST_URL).permitAll()
			    .antMatchers("/**").authenticated());
	}
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- TESTING -->

## Testing

### Unit Testing

We are using the `JUnit 5` for the unit testing. <br/>
The following is one of the sample test case

**StringUtilityTest.java**
```java
@ParameterizedTest
@DisplayName("Reverse String")
@CsvSource(value = { "abc, cba", "abc123, 321cba", "'', Invalid input", "NIL, Invalid input" }, nullValues = "NIL")
void testReverseString(String input, String expected) {
    String actual = StringUtility.reverseString(input);
    assertThat(actual).isEqualTo(expected);
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

### Integration Testing

We are using the `Mockito` framework for the integration testing. <br/>
This method of testing is used primarily to test the restful endpoint. <br/>
The following is one of the sample test case

**ReplyControllerv2Test.java**
```java
@DisplayName("v2 - with rule 11")
@Test
void testv1_withrule11() throws Exception {
    String input = TestV2Constant.V2_RULE_11_INPUT;

    String message = input.split("-")[1];
    String response = StringUtility.reverseString(StringUtility.reverseString(message));

    log.debug("Test: Rule 11 - {}", input);
    String endpointPath = String.format("%s/%s", TestV2Constant.V2_ENDPOINT, input);
    mock.perform(get(endpointPath).with(httpBasic(TestV2Constant.V2_API_USER, TestV2Constant.V2_API_PASSWORD))
            .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
            .andExpect(status().isOk())
            .andExpect(jsonPath(TestV2Constant.V2_RESPONSE_MESSAGE_JSONPATH, is(response))).andReturn();
}
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- DOCUMENTATION -->

## Documentation

### Swagger

`Swagger` is used to create documentation of the Restful APIs. Upon the adding of the dependency, there are some configuration to perform

**SecurityConstant.java**

The following are list of paths for the `Swagger` ui to be excluded.

```java
public static String[] SWAGGER_WHITELIST_URL = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui/**", "/webjars/**", };
```

<br/>

**SwaggerConfig.java**

The following will provide the `Swagger` web interface with:
* Path to include with the `apis` and `paths`
* API Info consists of the contacts for the developer
* Enable `Basic Authentication`
* Ensure that the `Basic Authentication` token is stored

```java
@Bean
public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage(SwaggerConstant.BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
}
```

Once the configuration is done, you will be able to access `Swagger` web interface, where we can try out the endpoint to see if it working. <br>

The following is the path for the `Swagger` web interface: <br/>

**[NOTE]** Replace {{ server.ip }} and {{ server.port }} accorddingly <br/>

```
http://{{ server.ip }}:{{ server.port }}/swagger-ui/
```
| ![Swagger Web Interface][img-swagger-web-interface] |
|:--:|
| *Swagger Web Interface* |

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- DEPLOYMENT -->

## Deployment

### Gradle

To build the project locally, run the following command:

```bash
./gradlew build
```

**[NOTE]** If the build was successful, the `executable jar` can be found in the *build/libs* directory 

<p align="right">(<a href="#top">back to top</a>)</p>

### Github Actions

As we are using `AWS` for the deployment to `Elasticbeanstalk`, we will need to generate the `AWS Secret key` and `access code` to run the deployment programmatically.

**AWS User Access ID**

In order to deploy the application to `AWS Elastic Beanstalk`, we will need to use the AWS User `Access ID` and `Secret Key`.

| ![Github-Action-AWS-Tokens][img-github-action-aws-tokens] |
|:--:|
| *Github Action AWS Tokens* |

**Github Secrets**

The `Access ID` and `Secret Key` will be stored in the `Github` *secrets and variables* of the repository 

| ![Github-Action-Secrets][img-github-action-secrets] |
|:--:|
| *Github Action Secrets* |

**Github Workflow**

The following config file performs the following on each successful push to the `master` branch:
* `Build Source Codes to Executable Jar` - Checkout the codes from the repository and build an executable jar
    * `Checkout Codes` 
    * `Setup JDK and Download Dependencies`
    * `Build Source Codes and Generate Executable Jar`
    * `Upload Build Artifact`
* `Deploy to AWS Elasticbeanstalk` - Deploy the executable jar into AWS Elastic Beanstalk
    * `Download a Build Artifact`
    * `Deploy to AWS Elastic Beanstalk`

**main.yml**
```yaml
name: Deploy to AWS Elasticbean Stalk
run-name: ${{ github.actor }} 
on: 
  push:
    branches:
      - master
jobs:
  # Build Codes with Gradle
  build:
    name: Build Source Codes to Executable Jar
    runs-on: ubuntu-latest
    steps:
      # https://github.com/marketplace/actions/checkout
      - name: Checkout Codes
        uses: actions/checkout@v4

      # https://github.com/marketplace/actions/setup-java-jdk
      - name: Setup JDK and Download Dependencies
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '11'
          cache: 'gradle'

      # Build Source Codes
      - name: Build Source Codes and Generate Executable Jar
        run: ./gradlew build --no-daemon

      # Get Executable Jar Location
      - name: Locate Executable Jar
        run: find . -type f -name "rest-service*jar"

      # Upload Executable Jar
      - name: Upload the Build Artifact
        uses: actions/upload-artifact@v3.1.3
        with:
          # Artifact name, default is artifact
          name: artifact
          # A file, directory or wildcard pattern that describes what to upload
          path: build/**/rest-service-*-SNAPSHOT.jar
          # 'warn' or 'ignore' are also available, defaults to `warn`
          if-no-files-found: error
  # Deploy Executable Jar to AWS Elasticbeanstalk
  deploy:
    name: Deploy to AWS Elasticbeanstalk
    # Depend on result of build
    needs: build
    runs-on: ubuntu-latest
    steps:
      # https://github.com/marketplace/actions/download-a-build-artifact
      - name: Download a Build Artifact
        uses: actions/download-artifact@v3
        with:
          # Artifact name
          name: artifact
      
      # Get Executable Jar Location
      - name: Locate Executable Jar
        run: find . -type f -name "rest-service*jar"

      # https://github.com/marketplace/actions/beanstalk-deploy
      - name: Deploy to AWS Elasticbeanstalk
        uses: einaregilsson/beanstalk-deploy@v21
        with:
          aws_access_key: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws_secret_key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          application_name: StringReply
          environment_name: StringReply-env
          version_label: ${{ github.sha }}
          region: ap-southeast-1
          deployment_package: libs/rest-service-0.0.1-SNAPSHOT.jar
          # use_existing_version_if_available: true

```

**Github Action Execution**

The following shows a sample of a successful execution of the `Github` Action

| ![Github-Action-Execution][img-github-action-execution] |
|:--:|
| *Github Action Execution* |

<p align="right">(<a href="#top">back to top</a>)</p>

### AWS Elastic Beanstalk

**application.properties**

By default, `Elastic Beanstalk` will listen to port `5000` to forward request. As such, we will need to ensure that our application is running on the port `5000`, which can be set in the `application.properties`

```bash
server.port=5000
```

**Environment**

In order to run the application in `Elastic Beanstalk`, we will need to create an environment to house the deployment. 

| ![AWS-Elastic-Beanstalk-Environment][img-aws-elastic-beanstalk-environment] |
|:--:|
| *AWS Elastic Beanstalk Environment* |

**Application Version**

With each successful deployment, the version will be updated.

| ![AWS-Elastic-Beanstalk-Application-Version][img-aws-elastic-beanstalk-application-version] |
|:--:|
| *AWS Elastic Beanstalk Application Version* |

**Monitoring**

Once the environment is setup, we will then have some metrics of the application health with the *Monitoring* tab

| ![AWS-Elastic-Beanstalk-Monitoring][img-aws-elastic-beanstalk-monitoring] |
|:--:|
| *AWS Elastic Beanstalk Monitoring* |

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- USAGE -->

## Usage

### Via Swagger

`Swagger` not only provides documentation for the restful api, it also serves as a platform for user to try out the api itself. We'll be using a dummy user account to test the api.

**application.properties**

To create the dummy user, we can add the following entry to the `application.properties`:

```bash
spring.security.user.name=username
spring.security.user.password=password
```

**Swagger Web Interface**

Since the application is also deployed in `AWS Elastic Beanstalk`, we'll use the domain provide by it to test restful api in the `Swagger` web interface.

|#|Item|Description|Value|
|--|--|--|--|
|1|dummy_user|Username to test the restful api|`username`|
|2|dummy_pass|Password to test the restful api|`password`|
|3|aws_domain|The domain which the application is deployed to|<a href="http://stringreply-env.eba-xm8yppwn.ap-southeast-1.elasticbeanstalk.com/">aws-domain</a>|
|4|aws_swagger|The `Swagger` web interface for the restful api|<a href="http://stringreply-env.eba-xm8yppwn.ap-southeast-1.elasticbeanstalk.com/swagger-ui/">aws-swagger</a>

| ![Swagger-Web-Interface][img-swagger-web-interface] |
|:--:|
| *Swagger Web interface* |

**Swagger Walkthrough**

To make a restful call in swagger, we will first need to login by selecting the `authorize` button and provide the `username` and `password` for the `Basic Authentication`

| ![Swagger-Authorization][img-swagger-authorization] |
|:--:|
| *Swagger Authorization* |

After providing the authorization, the next action is to enable the restful api with the `Try it out` button

| ![Swagger-Enable-Restful-API][img-swagger-enable-restful-api] |
|:--:|
| *Swagger Enable Restful API* |

To test the restful api, we'll just input the *message* and select the `Execute` button

| ![Swagger-Restful-API-Request][img-swagger-api-request] |
|:--:|
| *Swagger Restful API Request* |

The result will be shown in the `Responses` panel

| ![Swagger-Restful-API-Response][img-swagger-api-response] |
|:--:|
| *Swagger Restful API Response* |

<p align="right">(<a href="#top">back to top</a>)</p>

<!--  FUTURE CONSIDERATIONS -->

## Future Considerations

The following are some future considerations for the application
* `Database` Integration
* `Cache` Integration
* `OAuth` Integration

<p align="right">(<a href="#top">back to top</a>)</p>

### Database Integration

The current application lacks integration with a database.

One potential scenario for incorporating database integration is to store the requests made for analytics or monitoring purposes.

By extracting the ip address from the request header, we can determine the most frequent location from which the requests originate. This information can be used to optimize the application's performance by deploying it closer to the user. Additionally, it can assist in managing cloud resources more effectively, allowing for resource allocation based on peak and off-peak usage patterns.

Moreover, the ip address can also serve as a security measure. In the event of a sudden surge in requests from a specific range of IP addresses, it may indicate malicious activity. To mitigate this, the ability to block requests from potentially harmful actors can be implemented.

<p align="right">(<a href="#top">back to top</a>)</p>

### Cache Integration

Currently, the application repeatedly sends messages to a backend service for processing. This can lead to duplicate messages being processed unnecessarily, which can slow down the RESTful API. 

Implementing a caching mechanism would help improve performance by storing processed messages and serving them directly from the cache for subsequent requests. This would reduce the load on the backend service and minimize the occurrence of duplicate processing. 

<p align="right">(<a href="#top">back to top</a>)</p>

### OAuth Integration

The application currently uses basic authentication to verify users for accessing the RESTful API. 

However, basic authentication is unsafe because an attacker could intercept and decode the username and password during transmission. Therefore, a more secure authentication mechanism is needed. 

One option is to use OAuth tokens, which are temporary access tokens that can be revoked if compromised. OAuth tokens also provide granular control over user access to the RESTful API.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- [Restful API Unit Testing][ref-rest-api-testing-1]
- [Restful API Unit Testing][ref-rest-api-testing-2]
- [Restful API Unit Testing][ref-rest-api-testing-3]
- [Restful API Exception Handling][ref-rest-api-exception-1]
- [Restful API Exception Handling][ref-rest-api-exception-2]
- [Restful API AWS/Github Actions][ref-rest-api-aws-deployment-1]
- [Restful API AWS/Github Actions][ref-rest-api-aws-deployment-2]
- [Restful API AWS/Github Actions][ref-rest-api-aws-deployment-3]
- [Restful API Basic Authentication][ref-rest-api-security-1]
- [Restful API Swagger][ref-rest-api-swagger-1]
- [Restful API Swagger][ref-rest-api-swagger-2]
- [Restful API Swagger][ref-rest-api-swagger-3]
- [Readme Template][template-resource]

<p align="right">(<a href="#top">back to top</a>)</p>

---

<!-- MARKDOWN LINKS & IMAGES -->

[template-resource]: https://github.com/othneildrew/Best-README-Template/blob/master/README.md

[ref-rest-api-testing-1]: https://www.javaguides.net/2022/03/spring-boot-unit-testing-crud-rest-api-with-junit-and-mockito.html
[ref-rest-api-testing-2]: https://www.springboottutorial.com/integration-testing-for-spring-boot-rest-services
[ref-rest-api-testing-3]: https://docs.spring.io/spring-security/reference/servlet/test/mockmvc/http-basic.html
[ref-rest-api-exception-1]: https://www.baeldung.com/spring-mvc-controller-custom-http-status-code
[ref-rest-api-exception-2]: https://www.baeldung.com/exception-handling-for-rest-with-spring
[ref-rest-api-aws-deployment-1]: https://medium.com/javarevisited/how-to-deploy-springboot-app-to-elastic-beanstalk-using-github-actions-ci-cd-30b4557b4fb8
[ref-rest-api-aws-deployment-2]: https://virendraoswal.com/aws-elastic-beanstalk-cicd-with-github-actions
[ref-rest-api-aws-deployment-3]: https://federicomete.medium.com/ci-cd-with-github-actions-and-aws-in-3-steps-b603a1483d8e

[ref-rest-api-security-1]: https://howtodoinjava.com/spring-boot2/security-rest-basic-auth-example/

[ref-rest-api-swagger-1]: https://stackoverflow.com/questions/37671125/how-to-configure-spring-security-to-allow-swagger-url-to-be-accessed-without-aut
[ref-rest-api-swagger-2]: https://stackoverflow.com/questions/50024317/add-swagger-basic-auth-to-spring-boot-app
[ref-rest-api-swagger-3]: https://stackoverflow.com/questions/58110036/basic-authentication-using-swagger-ui

[img-use-case]: ./images/use_case.png
[img-reverse-string-design]: ./images/reverse_string_design.png
[img-hash-string-design]: ./images/hash_string_design.png

[img-github-action-aws-tokens]: ./images/github-action-aws-tokens.png
[img-github-action-secrets]: ./images/github-action-secrets.png
[img-github-action-execution]: ./images/github-action-successful-run.png

[img-aws-elastic-beanstalk-environment]: ./images/aws-elastic-beanstalk-environment.png
[img-aws-elastic-beanstalk-application-version]: ./images/aws-elastic-beanstalk-application-version.png
[img-aws-elastic-beanstalk-monitoring]: ./images/aws-elastic-beanstalk-monitoring.png

[img-swagger-web-interface]: ./images/swagger-web-interface.png
[img-swagger-authorization]: ./images/swagger-authorization.png
[img-swagger-enable-restful-api]: ./images/swagger-enable-restful-api.png
[img-swagger-api-request]: ./images/swagger-restful-api-request.png
[img-swagger-api-response]: ./images/swagger-restful-api-response.png

<!-- [![Deploy to AWS Elasticbean Stalk](https://github.com/D3vYuan/string-reply-coding-assignment/actions/workflows/main.yml/badge.svg)](https://github.com/D3vYuan/string-reply-coding-assignment/actions/workflows/main.yml)
[![Synk](https://snyk.io/test/github/D3vYuan/string-reply-coding-assignment/badge.svg)](https://snyk.io/test/github/D3vYuan/string-reply-coding-assignment)
[![Maintainability](https://api.codeclimate.com/v1/badges/069d23a4da9969dc4c29/maintainability)](https://codeclimate.com/github/D3vYuan/string-reply-coding-assignment/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/069d23a4da9969dc4c29/test_coverage)](https://codeclimate.com/github/D3vYuan/string-reply-coding-assignment/test_coverage)
[![Coverage](.github/badges/jacoco.svg)]
[![HitCount](https://hits.dwyl.com/D3vYuan/D3vYuan/string-reply-coding-assignment.svg?style=flat&show=unique)](http://hits.dwyl.com/D3vYuan/D3vYuan/string-reply-coding-assignment) -->