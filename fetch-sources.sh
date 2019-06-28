#!/bin/bash
set -eo pipefail
VERSION="$1"
URL="https://github.com/envoyproxy/java-control-plane/archive/v$VERSION.tar.gz"
rm -rf src/main/protobuf
wget $URL -O java-control-plane.tar.gz
tar xvf java-control-plane.tar.gz --strip-components=4 java-control-plane-$VERSION/api/src/main/proto 
rm -f java-control-plane.tar.gz
mv proto src/main/protobuf
