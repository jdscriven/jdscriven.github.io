#!/usr/bin/bash

rm web/index.html
mkdir -p web/posts
while read post
do
  title=$(<$post/post egrep "^@@@title" | cut -d ' ' -f2-)
  <templates/post-link sed "s/@@@name@@@/$title/g" | sed "s|@@@link@@@|$post|g" >>  .index.md
  mkdir -p web/$post
  pushd $post
  <post egrep -v "^@@@" | xargs -d '\n' -n1 -P1 ../../codify | ../../inline-codify| markdown > .index.md.1
  <../../templates/post sed "s/@@@title@@@/$title/g" | sed -e '/@@@content@@@/{r .index.md.1' -e 'd;}' > ../../web/$post/index.html
  rm .index.md.1
  [[ -e images ]] && cp -r images ../../web/$post/
  popd
done < post-list;
#cat templates/header | sed "s/@@@title@@@/$title/g" > index.html
cat .index.md | markdown > .index.md.1
cat templates/main | sed "s/@@@title@@@/Reific/g" | sed -e '/@@@content@@@/{r .index.md.1' -e 'd;}' > web/index.html
cp -r css web
rm .index.md.1
rm .index.md
