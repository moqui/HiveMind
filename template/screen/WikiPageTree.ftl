<#--
This software is in the public domain under CC0 1.0 Universal.

To the extent possible under law, the author(s) have dedicated all
copyright and related and neighboring rights to this software to the
public domain worldwide. This software is distributed without any
warranty.

You should have received a copy of the CC0 Public Domain Dedication
along with this software (see the LICENSE.md file). If not, see
<http://creativecommons.org/publicdomain/zero/1.0/>.
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
