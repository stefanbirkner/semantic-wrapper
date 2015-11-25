# Semantic Wrapper

[![Build Status](https://travis-ci.org/stefanbirkner/semantic-wrapper.svg?branch=master)](https://travis-ci.org/stefanbirkner/semantic-wrapper)

Semantic Wrapper generates wrapper classes for other classes in order
to provide type safety and semantics.

Semantic Wrapper is published under the
[MIT license](http://opensource.org/licenses/MIT).


## Installation

Semantic Wrapper is available from
[Maven Central](http://search.maven.org/).

    <plugins>
      <plugin>
        <groupId>com.github.stefanbirkner</groupId>
        <artifactId>semantic-wrapper-maven-plugin</artifactId>
        <version>0.5.0</version>
      </plugin>
    </plugins>


## Usage

Semantic Wrapper's documentation is stored in the `gh-pages` branch and is
available online at
http://stefanbirkner.github.io/semantic-wrapper/index.html

## Contributing

You have three options if you have a feature request, found a bug or
simply have a question about Semantic Wrapper.

* [Write an issue.](https://github.com/stefanbirkner/semantic-wrapper/issues/new)
* Create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))
* [Write an email to mail@stefan-birkner.de](mailto:mail@stefan-birkner.de)


## Development Guide

Semantic Wrapper is build with [Maven](http://maven.apache.org/). If you want
to contribute code than

* Please write a test for your change.
* Ensure that you didn't break the build by running `mvn verify -Dgpg.skip`.
* Fork the repo and create a pull request. (See [Understanding the GitHub Flow](https://guides.github.com/introduction/flow/index.html))

The basic coding style is described in the
[EditorConfig](http://editorconfig.org/) file `.editorconfig`.

Semantic Wrapper supports [Travis CI](https://travis-ci.org/) for
continuous integration. Your pull request will be automatically build by
Travis CI.

## Release Guide

* Select a new version according to the
  [Semantic Versioning 2.0.0 Standard](http://semver.org/).
* Set the new version in `pom.xml` and in the `Installation` section of
  this readme.
* Commit the modified `pom.xml` and `README.md`.
* Run `mvn clean deploy` with JDK 6.
* Add a tag for the release: `git tag semantic-wrapper-X.X.X`
* Push the commit: `git push origin master`
* Push the tag: `git push origin semantic-wrapper-X.X.X`
