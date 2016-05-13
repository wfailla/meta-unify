FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# for the unify project we use a special branch which
# holds the visualization
SRC_URI = "git://github.com/carosio/isis;protocol=https;branch=unify"
SRCREV = "67f07c2a96620ee1eab6bbf0626f198804c7da58"

