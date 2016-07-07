SUMMARY="Gunicorn a Python WSGI HTTP Server for UNIX"
DESCRIPTION="Gunicorn 'Green Unicorn' is a Python WSGI HTTP Server for UNIX. It's a pre-fork worker model ported from Ruby's Unicorn project."
LICENSE="CLOSED"

PR="r0"

SRC_URI = "https://github.com/benoitc/gunicorn/archive/${PV}.tar.gz"

DEPENDS="python-pip"
RDEPENDS_${PN} = "un-orchestrator python-pip gunicorn falcon cython requests"

do_install() {
  pip install -U ${S} --root ${D}
}
