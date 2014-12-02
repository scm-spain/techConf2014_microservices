#!/bin/bash

ps aux | grep gradle | grep $USER | awk '{print $2}' | xargs kill -9