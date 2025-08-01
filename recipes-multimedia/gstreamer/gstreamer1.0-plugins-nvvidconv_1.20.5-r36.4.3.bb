DESCRIPTION = "NVIDIA video converter GStreamer plugin"
SECTION = "multimedia"
LICENSE = "BSD-3-Clause & Proprietary"
LIC_FILES_CHKSUM = "file://nvbufsurface.h;endline=9;md5=ba9f51385aebf755c973fc0a6b2277ad \
                    file://README.txt;endline=26;md5=d4da79f8cebc6b73ce481b090afa99ae \
"

TEGRA_SRC_SUBARCHIVE = "Linux_for_Tegra/source/gst-nvvidconv_src.tbz2"
require recipes-bsp/tegra-sources/tegra-sources-36.4.3.inc

DEPENDS = "gstreamer1.0 glib-2.0 gstreamer1.0-plugins-base tegra-libraries-multimedia tegra-mmapi cuda-driver cuda-cudart"

SRC_URI += "\
    file://0001-Update-makefile-for-OE-builds.patch \
    file://0002-Use-filter-function-for-fixating-caps.patch \
"

S = "${UNPACKDIR}/gst-nvvidconv"

inherit pkgconfig features_check

EXTRA_OEMAKE = "CUDA_VER=${CUDA_VERSION}"

REQUIRED_DISTRO_FEATURES = "opengl"

do_install() {
	oe_runmake install DESTDIR="${D}"
}
FILES:${PN} = "${libdir}/gstreamer-1.0"
