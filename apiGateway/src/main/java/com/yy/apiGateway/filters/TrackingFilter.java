package com.yy.apiGateway.filters;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    private FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent() {
        return filterUtils.getCorrelationId() != null;

    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }


    @Override
    public Object run() throws ZuulException {
        if(isCorrelationIdPresent())
            logger.debug("mm-correlation-id found in tracking filter: {}."
                    , filterUtils.getCorrelationId());
        else {
            filterUtils.setCorrelationId(generateCorrelationId());
        }

        logger.debug("mm-correlation-id is generated in tracking filter: {}."
                , filterUtils.getCorrelationId());
//        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("Processing incoming request for {}."
        , RequestContext.getCurrentContext().getRequest().getRequestURI());
        return null;
    }
}
