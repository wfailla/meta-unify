SUMMARY="Intermediate module sitting between un-orchestrator and upper layers of the Unify architecture."
DESCRIPTION="The Virtualizer is an intermediate module sit between the un-orchestrator and the upper layers of the Unify architecture."
LICENSE="CLOSED"

PR="r0"

SRC_URI = "file://virtualizer.tar"

DEPENDS="python-pip"
RDEPENDS_${PN} = "un-orchestrator python-pip gunicorn falcon python-cython requests"

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
