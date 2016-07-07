SUMMARY="Intermediate module sitting between un-orchestrator and upper layers of the Unify architecture."
DESCRIPTION="The Virtualizer is an intermediate module sit between the un-orchestrator and the upper layers of the Unify architecture."
LICENSE="CLOSED"

PR="r0"

SRC_URI = "http://nas01/wfailla/unify/virtualizer.tar"

SRC_URI[md5sum] = "4a05571e92baa329991e5ade4a956978"
SRC_URI[sha256sum] = "b0e05c1d527c02b76f071cc56173835d593f9bdf43de6068407fd53ca2304187"

DEPENDS="python-pip"
RDEPENDS_${PN} = "un-orchestrator python-pip gunicorn falcon python-cython python-requests"

do_install() {

}
# RUN V:
# gunicorn -b ip:port virtualizer:api
# where 'ip' and 'port' must be set to the desired values.
# Please, note that the Virtualizer requires the un-orchestrator and the
# name-resolver running in the server.
#
# TODO:
# * service file
# * recipies for dependencies maybe install all unsing python-pip
#   * gnunicorn
#     * http://gunicorn.org/
#   * falcon
#     * https://falconframework.org/
#     * https://falcon.readthedocs.io/en/latest/user/install.html
#   * cython
#     * http://cython.org/#documentation
#     * http://docs.cython.org/src/quickstart/install.html
