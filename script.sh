#!/bin/bash

Xvfb :21 -screen 0 1024x768x24 &
export DISPLAY=:21
