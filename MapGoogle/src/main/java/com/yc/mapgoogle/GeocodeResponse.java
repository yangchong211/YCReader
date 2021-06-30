package com.yc.mapgoogle;


import java.util.List;

public class GeocodeResponse {
    private GeocodeResponse.PlusCodeBean plus_code;
    private String status;
    private List<GeocodeResponse.ResultsBean> results;

    public GeocodeResponse() {
    }

    public GeocodeResponse.PlusCodeBean getPlus_code() {
        return this.plus_code;
    }

    public void setPlus_code(GeocodeResponse.PlusCodeBean plus_code) {
        this.plus_code = plus_code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeocodeResponse.ResultsBean> getResults() {
        return this.results;
    }

    public void setResults(List<GeocodeResponse.ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String formatted_address;
        private GeocodeResponse.ResultsBean.GeometryBean geometry;
        private String place_id;
        private GeocodeResponse.ResultsBean.PlusCodeBeanX plus_code;
        private List<GeocodeResponse.ResultsBean.AddressComponentsBean> address_components;
        private List<String> types;

        public ResultsBean() {
        }

        public String getFormatted_address() {
            return this.formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeocodeResponse.ResultsBean.GeometryBean getGeometry() {
            return this.geometry;
        }

        public void setGeometry(GeocodeResponse.ResultsBean.GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getPlace_id() {
            return this.place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public GeocodeResponse.ResultsBean.PlusCodeBeanX getPlus_code() {
            return this.plus_code;
        }

        public void setPlus_code(GeocodeResponse.ResultsBean.PlusCodeBeanX plus_code) {
            this.plus_code = plus_code;
        }

        public List<GeocodeResponse.ResultsBean.AddressComponentsBean> getAddress_components() {
            return this.address_components;
        }

        public void setAddress_components(List<GeocodeResponse.ResultsBean.AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<String> getTypes() {
            return this.types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class AddressComponentsBean {
            private String long_name;
            private String short_name;
            private List<String> types;

            public AddressComponentsBean() {
            }

            public String getLong_name() {
                return this.long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return this.short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return this.types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }

        public static class PlusCodeBeanX {
            private String compound_code;
            private String global_code;

            public PlusCodeBeanX() {
            }

            public String getCompound_code() {
                return this.compound_code;
            }

            public void setCompound_code(String compound_code) {
                this.compound_code = compound_code;
            }

            public String getGlobal_code() {
                return this.global_code;
            }

            public void setGlobal_code(String global_code) {
                this.global_code = global_code;
            }
        }

        public static class GeometryBean {
            private GeocodeResponse.ResultsBean.GeometryBean.LocationBean location;
            private String location_type;
            private GeocodeResponse.ResultsBean.GeometryBean.ViewportBean viewport;

            public GeometryBean() {
            }

            public GeocodeResponse.ResultsBean.GeometryBean.LocationBean getLocation() {
                return this.location;
            }

            public void setLocation(GeocodeResponse.ResultsBean.GeometryBean.LocationBean location) {
                this.location = location;
            }

            public String getLocation_type() {
                return this.location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public GeocodeResponse.ResultsBean.GeometryBean.ViewportBean getViewport() {
                return this.viewport;
            }

            public void setViewport(GeocodeResponse.ResultsBean.GeometryBean.ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class ViewportBean {
                private GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.NortheastBean northeast;
                private GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.SouthwestBean southwest;

                public ViewportBean() {
                }

                public GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.NortheastBean getNortheast() {
                    return this.northeast;
                }

                public void setNortheast(GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.SouthwestBean getSouthwest() {
                    return this.southwest;
                }

                public void setSouthwest(GeocodeResponse.ResultsBean.GeometryBean.ViewportBean.SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class SouthwestBean {
                    private double lat;
                    private double lng;

                    public SouthwestBean() {
                    }

                    public double getLat() {
                        return this.lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return this.lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class NortheastBean {
                    private double lat;
                    private double lng;

                    public NortheastBean() {
                    }

                    public double getLat() {
                        return this.lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return this.lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }

            public static class LocationBean {
                private double lat;
                private double lng;

                public LocationBean() {
                }

                public double getLat() {
                    return this.lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return this.lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }
        }
    }

    public static class PlusCodeBean {
        private String compound_code;
        private String global_code;

        public PlusCodeBean() {
        }

        public String getCompound_code() {
            return this.compound_code;
        }

        public void setCompound_code(String compound_code) {
            this.compound_code = compound_code;
        }

        public String getGlobal_code() {
            return this.global_code;
        }

        public void setGlobal_code(String global_code) {
            this.global_code = global_code;
        }
    }
}

