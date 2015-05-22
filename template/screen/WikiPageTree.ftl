<#--
This is free and unencumbered software released into the public domain.
For specific language governing permissions and limitations refer to
the LICENSE.md file or http://unlicense.org
-->

<#macro pageList pageInfoList parentInPath=false>
    <#list pageInfoList as pageInfo>
        <#assign currentInPath = breadcrumbNameList.contains(pageInfo.name)>
        <li<#if currentInPath> class="jstree-open"</#if>><a<#if parentInPath && pageName == pageInfo.name> class="jstree-clicked"</#if> href="${baseLinkUrl}/${pageInfo.path}">${pageInfo.name}</a>
            <#if pageInfo.childResourceList?has_content>
                <ul>
                    <@pageList pageInfoList=pageInfo.childResourceList parentInPath=currentInPath/>
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
        $("#wiki-page-tree").bind('select_node.jstree', function(e,data) {
            window.location.href = data.node.a_attr.href;
        }).jstree({
            "plugins" : [ "themes" ],
            "core" : { "themes" : { "url" : true, "dots" : true, "icons" : false }, "multiple" : false}
        });
    });
</script>
</#if>
