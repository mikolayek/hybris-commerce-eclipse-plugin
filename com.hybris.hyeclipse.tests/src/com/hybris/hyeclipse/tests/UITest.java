package com.hybris.hyeclipse.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.swt.api.MenuItem;
import org.eclipse.reddeer.swt.impl.menu.ShellMenu;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
public class UITest {

	@Test
	public void testAppUIMenu() {
		ShellMenu menu = new ShellMenu();
		assertNotNull(menu);
		MenuItem menuItem = menu.getItem("SAP Hybris [y]");
		assertNotNull(menuItem);
		assertTrue(menuItem.isEnabled());
	}

	@Test
	@Ignore
	public void testProjectImport() {
//		SWTWorkbenchBot bot = new SWTWorkbenchBot();
//		SWTBotMenu result = bot.menu("File").menu("Import...").click();
//		bot.sleep(1000);
//		SWTBotShell shell = bot.shell("Import");
//		shell.activate();
//		bot.tree().expandNode("SAP Hybris [y]").select("Import SAP Hybris Platform");
//		bot.button("Next >").click();
//		assertNotNull(result);
	}

}
