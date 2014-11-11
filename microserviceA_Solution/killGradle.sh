#!/bin/bash

#ps ux | grep gradle | awk "BEGIN{FS=$USER} {print $2}" | awk -F' ' '{system("kill -9 " $2)}'
ps aux | grep gradle | grep $USER | awk '{print $2}' | xargs kill -9
