FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# for the unify project we use a special branch which
# holds the visualization
SRC_URI = "git://github.com/carosio/isis;protocol=https;branch=unify"
SRCREV = "9441b5f2229aa7f341f0f265a5416c37829ff966"

