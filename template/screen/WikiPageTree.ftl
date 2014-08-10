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
            "core" : { "themes" : { "url" : true, "dots" : true, "icons" : false }, "multiple" : false},
        });
    });
</script>
</#if>
