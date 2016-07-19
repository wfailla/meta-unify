SUMMARY="balancer"
LICENSE="CLOSED"

PR="r3"

SRC_URI = "http://nas01/non-public-unify-stuff/balancer_server.zip"
SRC_URI += "file://start.sh file://stop.sh"

SRC_URI[md5sum] = "399eb8357fc41ce59eeb1e37c49c9791"
SRC_URI[sha256sum] = "7f1915a566827359830acee2676574d2589c38f559670a96af1f51f60618b000"

S = "${WORKDIR}"

do_compile() {
	oe_runmake balancer_server
	mkdir -v nf-tar
	install -m 0755 balancer_server nf-tar/
	install -m 0755 start.sh nf-tar/start
	install -m 0755 stop.sh nf-tar/stop
	tar cvfz balancer-nf.tar.gz -C nf-tar .
}

do_install() {
	install -m 0755 -d ${D}/opt/unify/NFs/
	install -m 0644 -o 0 -g 0 ${WORKDIR}/balancer-nf.tar.gz ${D}/opt/unify/NFs/
    install -m 0755 -o 0 -g 0 ${WORKDIR}/balancer_server ${D}/usr/bin/
}

FILES_${PN} = "/opt/unify"

