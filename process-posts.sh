#!/usr/bin/bash

for post in posts/*; do
  pushd $post
  cat index.md | xargs -d '\n' -n1 -P1 ../../codify | markdown > index.html
  popd
done;
