#!/bin/bash
mkdir images

            
## declare an array variable
declare -a arr=("2490120000000" "7613035190467" "7613035190528")

## now loop through the above array
for i in "${arr[@]}"
do
   echo "$i"
  curl -H "Host: imagenes.preciosclaros.gob.ar" \
     -H "User-Agent:Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:66.0) Gecko/20100101 Firefox/66.0" \
     -H "Accept:image/webp,*/*" \
     -H "Accept-Language:en-US,en;q=0.5" \
     -H "Accept-Encoding:gzip, deflate, br" \
     -H "Referer:https://www.preciosclaros.gob.ar/" \
     -H "DNT:1"\
     -H "Connection:keep-alive"  \
     https://imagenes.preciosclaros.gob.ar/productos/"$i".jpg\
     --output "images/"$i".jpg"

done
