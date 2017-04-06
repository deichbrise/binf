#!/bin/bash

echo "$TRAVIS_REPO_SLUG"

if [ "$TRAVIS_REPO_SLUG" == "deichbrise/binf" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

    echo -e "Publishing javadoc...\n"
    # Get to the Travis build directory, configure git and clone the repo
    cd $HOME
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "travis-ci"
    git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/deichbrise/binf gh-pages > /dev/null

    # Commit and Push the Changes
    cd gh-pages
    git rm -rf ./javadoc-latest
    cp -Rf $HOME/javadoc ./javadoc-latest
    git add -f .
    git commit -m "Lastest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
    git push -fq origin gh-pages > /dev/null

    echo -e "Published Javadoc to gh-pages.\n"
fi