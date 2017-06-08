module UsePictures where

import Pictures

blackHorse :: Picture
blackHorse = invertColour horse

rotate :: Picture->Picture
rotate pic = flipV pic

rotateHorse :: Picture
rotateHorse = flipV horse

blackRetangle :: Picture
blackRetangle = superimpose horse blackHorse

tile :: Picture
tile = above (sideBySide white blackRetangle) (sideBySide blackRetangle white)

chess :: Picture
chess = above (sideBySide (sideBySide (above tile tile) (above tile tile)) (sideBySide (above tile tile) (above tile tile))) (sideBySide (sideBySide (above tile tile) (above tile tile)) (sideBySide (above tile tile) (above tile tile)))

tileHorse :: Picture
tileHorse = above (sideBySide horse blackHorse) (sideBySide blackHorse horse)