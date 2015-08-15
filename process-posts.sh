#!/usr/bin/bash

# location of generated content
#public=web

#rm $public/index.html
mkdir -p web
mkdir -p draft
while read destination post
do
  title=$(<posts/$post/post egrep "^@@@title" | cut -d ' ' -f2-)
  <templates/post-link sed "s/@@@name@@@/$title/g" | sed "s|@@@link@@@|$post|g" >>  .index.md
  mkdir -p $destination/$post
  pushd posts/$post
  <post egrep -v "^@@@" | xargs -d '\n' -n1 -P1 ../../codify | ../../inline-codify| markdown > .index.md.1
  <../../templates/post sed "s/@@@title@@@/$title/g" | sed -e '/@@@content@@@/{r .index.md.1' -e 'd;}' > ../../$destination/$post/index.html
  rm .index.md.1
  [[ -e images ]] && cp -r images ../../$destination/$post/
  popd
done < post-list;
#cat templates/header | sed "s/@@@title@@@/$title/g" > index.html
cat .index.md | markdown > .index.md.1
cat templates/main | sed "s/@@@title@@@/Reific/g" | sed -e '/@@@content@@@/{r .index.md.1' -e 'd;}' > .index.html

cp .index.html web/index.html
cp .index.html draft/index.html

cp -r css web
cp -r css draft

rm .index.md.1
rm .index.md
rm .index.html
