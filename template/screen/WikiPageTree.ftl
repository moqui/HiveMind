<#--
This Work is in the public domain and is provided on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
including, without limitation, any warranties or conditions of TITLE,
NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE.
You are solely responsible for determining the appropriateness of using
this Work and assume any risks associated with your use of this Work.

This Work includes contributions authored by David E. Jones, not as a
"work for hire", who hereby disclaims any copyright to the same.
-->

<#macro pageList pageInfoList>
    <#list pageInfoList as pageInfo>
        <li id="PLI_${pageInfo.path.hashCode().toString()}"><a href="${baseLinkUrl}/${pageInfo.path}">${pageInfo.name}</a>
            <#if pageInfo.childResourceList?has_content>
                <ul>
                    <@pageList pageInfoList=pageInfo.childResourceList/>
                </ul>
            </#if>
        </li>
    </#list>
</#macro>

<#if rootChildResourceList?has_content>
<div id="wiki-page-tree">
    <ul>
        <@pageList pageInfoList=rootChildResourceList/>
    </ul>
</div>

<script>
    $(function () {
        var openList = [<#list breadcrumbMapList?if_exists as breadcrumbMap>'PLI_${breadcrumbMap.pagePath.hashCode().toString()}'<#if breadcrumbMap_has_next>,</#if></#list>];
        $.jstree._themes = "/hmstatic/jstree/";
        $("#wiki-page-tree").jstree({
            "plugins" : [ "themes", "html_data" ],
            "core" : { "initially_open" : openList },
            "themes" : { "theme" : "classic", "dots" : true, "icons" : false } });
    });
</script>
</#if>
